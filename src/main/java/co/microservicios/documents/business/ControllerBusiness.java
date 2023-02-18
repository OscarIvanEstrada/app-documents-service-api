package co.microservicios.documents.business;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.microservicios.documents.dto.GeneratePDFRequestDTO;
import co.microservicios.documents.enums.ErrorEnum;
import co.microservicios.documents.exceptions.ApiException;
import co.microservicios.documents.model.DocumentEntity;
import co.microservicios.documents.model.ProductoEntity;
import co.microservicios.documents.repository.DocumentRepository;
import co.microservicios.documents.repository.ProductoRepository;
import co.microservicios.documents.util.LoggerUtil;


@Component
public class ControllerBusiness {

	@Autowired
	LoggerUtil log;

	@Autowired
    ProductoRepository productoRepository;
	
	@Autowired
    DocumentRepository documentRepository;
	
	
	public String generateDoc(GeneratePDFRequestDTO request) {

		try {
			
			if(request.getTemplate() == null || request.getTemplate().isEmpty() || request.getTemplate().length() <= 100) {
				request.setTemplate(documentRepository.findById("f4e9f7cb-42d5-40c6-91ae-ba973115129d").get().getTemplate());
			}
			
			byte[] bytes = Base64.getDecoder().decode(request.getTemplate());
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new ByteArrayInputStream(bytes));
			MainDocumentPart contentAccessor = wordMLPackage.getMainDocumentPart();
			
			this.replace(request,contentAccessor);
			Docx4J.save(wordMLPackage, outputStream);
			
			InputStream doc = new ByteArrayInputStream(outputStream.toByteArray());
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			XWPFDocument document = new XWPFDocument(doc);
			PdfOptions options = PdfOptions.create();
			PdfConverter.getInstance().convert(document, out, options);

			return Base64.getEncoder().encodeToString(out.toByteArray());
			
		}catch(Exception e) {
			throw new ApiException(ErrorEnum.TECHNICAL,e.getMessage());
		}

	}
	
	 private void replace(GeneratePDFRequestDTO request, ContentAccessor contentAccessor) {
		    ObjectFactory factory = new ObjectFactory();
			R run = factory.createR();
			
			for (int i = 0; i < contentAccessor.getContent().size() ; i++) {
				Object obj = contentAccessor.getContent().get(i);
				String str = (obj.toString());
			    
				List<String> keys = isKeyPresent(str,request.getData());
		    	if (keys != null && keys.size() > 0) {
					
		    		 if (obj instanceof P) {
		    			 
		    			 str = replaceKeys(str,request.getData(),keys);
		    			 Text text = factory.createText();
		    			 text.setValue(str);

		    			 P paragraph = ((P) obj);
		    			 paragraph.getContent().removeAll(paragraph.getContent());		    			 
		    			 paragraph.getContent().add(run);
		    			 run.getContent().add(text);
		    			 contentAccessor.getContent().set(i,paragraph);
		    			 
		    		 }
		    			
				}
			
			}
	}

	private String replaceKeys(String str, Map<String, String> data, List<String> keys) {
		
		for(String k : keys) {
			 str = str.replace(k, data.get(k));	
		}
		 
		return str;
	}

	private List<String> isKeyPresent(String str, Map<String, String> data) {
		List keys = new ArrayList<String>();
		for (Map.Entry<String, String> entry : data.entrySet()) {
		    if(str.contains(entry.getKey())) {
		    	keys.add(entry.getKey());
		    }
		}
	    
		return keys;
	}

	public void addDataProducto(ProductoEntity data) {
		productoRepository.save(data);
	}

	public List<ProductoEntity> getDataProducto() {
		List<ProductoEntity> result = new ArrayList<ProductoEntity>();
		productoRepository.findAll().forEach((final ProductoEntity r) -> result.add(r));
		return result;
	}
	
	public List<DocumentEntity> getDataDocument() {
		return (List<DocumentEntity>) documentRepository.findAll();
	}

	public void saveDocument(DocumentEntity data) {
		documentRepository.save(data);
	}



}

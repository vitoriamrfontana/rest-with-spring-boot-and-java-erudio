package vitoriamrfontana.file.importer.contract;

import vitoriamrfontana.data.dto.v1.PersonDTO;

import java.io.InputStream;
import java.util.List;

public interface FileImporter {
    List<PersonDTO> importFile(InputStream inputStream) throws Exception;
}

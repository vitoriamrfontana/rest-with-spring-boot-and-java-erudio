package vitoriamrfontana.file.exporter.contract;

import org.springframework.core.io.Resource;
import vitoriamrfontana.data.dto.v1.PersonDTO;

import java.io.InputStream;
import java.util.List;

public interface FileExporter {
    Resource exportFile(List<PersonDTO> people) throws Exception;
    Resource exportPerson(List<PersonDTO> person) throws Exception;
}

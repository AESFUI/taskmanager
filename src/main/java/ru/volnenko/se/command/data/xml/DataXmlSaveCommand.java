package ru.volnenko.se.command.data.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.nio.file.Files;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.constant.DataConstant;
import ru.volnenko.se.entity.Domain;
import ru.volnenko.se.service.DomainService;

/**
 * @author Denis Volnenko
 */
@Component
public final class DataXmlSaveCommand extends AbstractCommand {

    @Resource
    private DomainService domainService;

    @Override
    public String command() {
        return "data-xml-save";
    }

    @Override
    public String description() {
        return "Save Domain to XML.";
    }

    @Override
    public void execute() throws Exception {
        System.out.println("[DATA XML SAVE]");
        final Domain domain = new Domain();
        domainService.export(domain);
        final ObjectMapper objectMapper = new XmlMapper();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        final String json = objectWriter.writeValueAsString(domain);
        final byte[] data = json.getBytes("UTF-8");
        final File file = new File(DataConstant.FILE_XML);
        Files.write(file.toPath(), data);
        System.out.println("[OK]");
    }

}

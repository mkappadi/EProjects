import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.event.report.HtmlReportGenerator;
import org.milyn.io.StreamUtils;
import org.milyn.container.ExecutionContext;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Main {

    protected static String runSmooksTransform(byte[] messageIn) throws IOException, SAXException, SmooksException {

        Smooks smooks = new Smooks("smooks-config-cfl.xml");

        try {
            ExecutionContext executionContext = smooks.createExecutionContext();
            StringWriter writer = new StringWriter();

            // Configure the execution context to generate a report...
            //executionContext.setEventListener(new HtmlReportGenerator("target/report/report.html"));

            smooks.filterSource(executionContext, new StreamSource(new InputStreamReader(new ByteArrayInputStream(messageIn), "UTF-8")), new StreamResult(writer));

            return writer.toString();
        } finally {
            smooks.close();
        }
    }

    public static void main(String[] args) throws IOException, SAXException, SmooksException {
        
    	byte[] messageIn = readInputMessage();
    	System.out.println("\n\n==============Message In==============");
        System.out.println(new String(messageIn));
        System.out.println("======================================\n");

        String messageOut = Main.runSmooksTransform(messageIn);

        System.out.println("==============Message Out=============");
        System.out.println(messageOut);
        System.out.println("======================================\n\n");
    }

    private static byte[] readInputMessage() {
        try {
        	return StreamUtils.readStream(new FileInputStream("AN.txt"));
            //return StreamUtils.readStream(new FileInputStream("input.log"));
        	//return StreamUtils.readStream(new FileInputStream("input_message.txt"));
        	//return StreamUtils.readStream(new FileInputStream("ASN.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return "<no-message/>".getBytes();
        }
    }
}
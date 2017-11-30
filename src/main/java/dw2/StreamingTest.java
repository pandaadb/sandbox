package dw2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.server.ServerProperties;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class StreamingTest extends io.dropwizard.Application<Configuration> {

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 0);
        environment.jersey().register(Streamer.class);
    }

    public static void main(String[] args) throws Exception {
        new StreamingTest().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
    }

    @Path("/log")
    public static class Streamer {

        @GET
        @Produces("application/octet-stream")
        public Response test() {
            return Response.ok(new StreamingOutput() {
                @Override

                public void write(OutputStream output) throws IOException, WebApplicationException {
                    while (true) {
                        output.write("Test \n".getBytes());
                        output.flush(); // <-- This is very important. Do not
                                        // forget.
                        System.out.println("a");
                        try {
                            Thread.sleep(1000); // simulate waiting for stream
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).build();
        }
    }
}
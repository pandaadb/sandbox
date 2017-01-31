package sparky;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.Authentication;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.DependencyResolutionException;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import com.jcabi.aether.Aether;


public class MavneDLtest {

	public static void main(String[] args) throws DependencyResolutionException {
		
		File local = new File("/tmp/tmp_repo");
		
		RemoteRepository goRepo = new RemoteRepository("gomedia", "default", "https://repo.capmedia.io/repository/capmedia-release");
		goRepo.setAuthentication(new Authentication("artur", "FX_Studio37".toCharArray()));
		
	    Collection<RemoteRepository> remotes = Arrays.asList(
//	      new RemoteRepository(
//	        "maven-central",
//	        "default",
//	        "http://repo1.maven.org/maven2/"
//	      ), 
	      goRepo
	    );
	    
	    Collection<Artifact> deps = new Aether(remotes, local).resolve(
	      new DefaultArtifact("org.pdb", "repoter-test-scala_2.10", "jar",  "1.0"),
	      "runtime"
	    );
		
	    Artifact next = deps.iterator().next();
	    String absolutePath = next.getFile().getAbsolutePath();
	    System.out.println(absolutePath);
	}
	
}

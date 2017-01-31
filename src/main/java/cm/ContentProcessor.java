package cm;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public interface ContentProcessor {
	
	/**
	 * Attempts to pull new content. Handles all aspects of pulling, e.g. creating a temporary directory, replacing old content, validating content.
	 * @return
	 */
	public boolean tryPull();
	
	/**
	 * Copies a list of ids to a given destination, creating a simlink instead of a hard copy if needed.
	 * @param ids
	 * @param destination
	 * @param simlink
	 * @return
	 */
	public boolean copy(final List<String> ids, final Path destination, final boolean simlink);
	
	/**
	 * Validates a certain item identified by ID 
	 * @param id
	 * @return
	 */
	public boolean validate(final String id);
	
	/**
	 * Used to register self for scheduling the pulling.
	 * 
	 * @param scheduler
	 */
	public void scheduleSelf(final ScheduledExecutorService scheduler);
	
	/**
	 * Takes care of a full cleanup of items that can be removed
	 */
	public void cleanup();
}

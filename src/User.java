public class User {

	private String name;
	private String family;
	private String username;
	private String password;

	public User(String name, String family, String username, String password) {
		this.name = name;
		this.family = family;
		this.username = username;
		this.password = password;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username) {
		this.username = username;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getFamily() {
		return family;
	}

	protected void setFamily(String family) {
		this.family = family;
	}

	protected String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}
}
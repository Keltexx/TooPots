package es.uji.ei102718cln.TooPots.model;

public class Login {
	private String usuario;
	private String password;
	private String rol;
	
	public Login() {}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return "Login [usuario=" + usuario + ", password=" + password + ", rol=" + rol + "]";
	}
}

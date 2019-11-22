package ecoshop.backend;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class Usuario {
    
    private String nombreUsuario;
    private String email;
    private boolean esAdministrador;

    public Usuario(String nombre, String apellido, String nombreUsuario, String email, boolean esAdministrador) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.esAdministrador = esAdministrador;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }
    
    
    
}

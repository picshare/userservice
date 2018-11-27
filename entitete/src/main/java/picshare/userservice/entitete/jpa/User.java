package picshare.userservice.entitete.jpa;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity(name = "picshare_user")
@NamedQueries(value =
        {
                @NamedQuery(name = "User.getAll", query = "SELECT u FROM picshare_user u"),
                @NamedQuery(name = "User.deleteAll", query = "DELETE FROM picshare_user WHERE 1 = 1"),
                @NamedQuery(name = "User.getAllBySurname", query = "SELECT u FROM picshare_user u WHERE u.surname = :surname"),
                @NamedQuery(name = "User.deleteAllBySurname", query = "DELETE FROM picshare_user u WHERE u.surname = :surname")
        })
public class User implements Serializable {

    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    @XmlElement
    @Column(nullable = false)
    private String name;
    @XmlElement
    @Column(nullable = false)
    private String surname;
    @XmlElement
    private String username;
    @XmlElement
    private String email;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

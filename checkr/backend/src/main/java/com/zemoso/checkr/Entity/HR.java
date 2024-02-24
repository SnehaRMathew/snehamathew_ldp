import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HR {
    private long id;
    private String name;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private String email;
    private String status;
    private String mobile;
    private String address;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Candidate {
    private long id;
    private String name;
    private Date dateOfBirth;
    private Date enrolDate;
    private String email;
    private String status;
    private String mobile;
    private String address;
    private String location;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Case {
    private long id;
    private Candidate candidateID; // References Candidate ID
    private String description;
    private String status;
    private Date date;
    private HR createdBy; // References HR ID
    private Date createdDate;
    private HR updatedBy; // References HR ID
    private Date updatedDate;
}

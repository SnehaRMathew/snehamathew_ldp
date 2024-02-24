import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Engage {
    private long id;
    private Candidate candidateId;
    private String description;
    private Date date;
    private String status;
    private String note;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

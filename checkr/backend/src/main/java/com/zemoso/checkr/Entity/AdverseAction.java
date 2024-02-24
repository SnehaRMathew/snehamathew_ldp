import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdverseAction {
    private long id;
    private Candidate candidateId;
    private String description;
    private Date date;
    private String status;
    private AdverseActionNotification notificationId;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

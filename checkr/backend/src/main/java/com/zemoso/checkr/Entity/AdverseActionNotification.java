import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdverseActionNotification {
    private long id;
    private HR sender;
    private Candidate receiver;
    private String body;
    private Date preNoticeDate;
    private Date postNoticeDate;
    private String status;
    private HR createdBy;
    private Date createdDate;
    private HR updatedBy;
    private Date updatedDate;
}

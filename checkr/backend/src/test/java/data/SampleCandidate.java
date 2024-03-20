package data;

import com.zemoso.checkr.entity.CandidateDetails;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SampleCandidate {
    public static List<com.zemoso.checkr.entity.Candidate> getCandidates() {
        return getCandidateDetails().stream().map(c -> c.getCandidate()).collect(Collectors.toList());
    }

    public static List<CandidateDetails> getCandidateDetails() {
        List<CandidateDetails> candidates = IntStream.range(0, 20)
                .mapToObj(i-> {
                    return CandidateDetails.builder().id(i)
                            .candidate(com.zemoso.checkr.entity.Candidate.builder().id(i).name("Candidate" + i).email("candidate" + i + "@mail.com").build())
                            .build();
                })
                .collect(Collectors.toList());
        candidates.add(CandidateDetails.builder().id(candidates.size())
                .candidate(com.zemoso.checkr.entity.Candidate.builder().id(candidates.size()).name("James Mayer" ).email("james.meyer@mail.com").build())
                .build());
        return candidates;
    }
}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesPensionPlan {

    // Attributes
    String PlanReferenceNumber;
    LocalDate EnrollmentDate;
    float MonthlyContribution;

}

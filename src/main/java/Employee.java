import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    long Id;
    String firstName;
    String Lastname;
    LocalDate employmentDate;
    float yearlySalary;
    EmployeesPensionPlan plan;

    public static Employee[] emp_list = {
            new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.5f, new EmployeesPensionPlan("", LocalDate.of(2023, 1, 17), 100f)),
            new Employee(2, "Benard", "Shaw", LocalDate.of(2019, 4, 3), 195945.5f, null),
            new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 845945.5f, new EmployeesPensionPlan("", LocalDate.of(2019, 11, 4), 1055f)),
            new Employee(4, "Wesley", "Schneider", LocalDate.of(2019, 5, 2), 74945.5f, null)};

    public static void print(List<Employee> list) {

        List<Employee> listE = list.stream().filter(e -> e.employmentDate != null).
                sorted((o1, o2) -> o1.getLastname().compareTo(o2.getLastname())).collect(Collectors.toList());

        for (Employee item : listE) {


            JSONObject json = new JSONObject();
            json.put("Id", item.getId());
            json.put("FirstName", item.getFirstName());
            json.put("Lastname", item.getLastname());
            json.put("EmploymentDate", item.getEmploymentDate());
            json.put("YearlySalary", item.getYearlySalary());
            json.put("PlanReferenceNumber", item.getPlan().getPlanReferenceNumber());
            json.put("MonthlyContribution", item.getPlan().getMonthlyContribution());
            json.put("EnrollmentDate", item.getPlan().getEnrollmentDate());
            System.out.println(json);
        }
    }

    public static void MonthlyUpcomingEnrolment(Employee[] employees){

        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfNextMonth = currentDate.plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfNextMonth = firstDayOfNextMonth.withDayOfMonth(firstDayOfNextMonth.lengthOfMonth());

        List<Employee> upcomingEnrollees = Arrays.asList(employees).stream()
                .filter(emp -> emp.getPlan() == null && emp.getEmploymentDate().plusYears(5).isBefore(lastDayOfNextMonth.plusDays(1)))
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

        print(upcomingEnrollees);

    }

    public static void main(String[] args) {
        MonthlyUpcomingEnrolment(emp_list);

    }


}

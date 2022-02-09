package com.example.streampracticetask.practice;

import com.example.streampracticetask.model.*;
import com.example.streampracticetask.service.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        //TODO Implement the method
        return employeeService.readAll();
        //return new ArrayList<>();

        /**
         * return List.of(
         *                 new Employee(1L, "Jack", "Dorsy", "jackDorsy@hotmail.com", "215-895-2545", LocalDate.now(), new Job("01", "CEO", 100L, 200L), 150L, 0.04),
         *                 new Employee(2L, "Jackson", "Torsy", "jacksonTorsy@hotmail.com", "215-895-2545", LocalDate.now(), new Job("02", "CFO", 100L, 200L), 170L, 0.01),
         *                 new Employee(3L, "Jackie", "Horsy", "jackieHorsy@hotmail.com", "215-895-2545", LocalDate.now(), new Job("03", "CTO", 100L, 200L), 160L, 0.02),
         *                 new Employee(4L, "Hack", "Porsy", "hackPorsy@hotmail.com", "215-895-2545", LocalDate.now(), new Job("04", "CIO", 100L, 200L), 180L, 0.03),
         *                 new Employee(5L, "Pack", "Korsy", "packKorsy@hotmail.com", "215-895-2545", LocalDate.now(), new Job("05", "Chairman", 100L, 200L), 250L, 0.1)
         *         );
         *         in this case: in PracticeTest : exceptedValue = 5; then it will pass. But this is not what they want.
         */


    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        return countryService.readAll();
        //return new ArrayList<>();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        return departmentService.readAll();
        //return new ArrayList<>();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        return jobService.readAll();
        //return new ArrayList<>();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        return locationService.readAll();
        //return new ArrayList<>();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        return regionService.readAll();
        //return new ArrayList<>();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        return jobHistoryService.readAll();
        //return new ArrayList<>();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        return employeeService.readAll().stream()
                .map(Employee::getFirstName).collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        return countryService.readAll().stream()
                .map(Country::getCountryName)
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        return departmentService.readAll().stream()
                .map(Department::getManager).map(Employee::getFirstName)
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        return departmentService.readAll().stream()
                .filter(department -> department.getManager().getFirstName().equals("Steven"))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        return departmentService.readAll().stream()
                .filter(department-> department.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                //.findFirst().get().getLocation().getCountry().getRegion();
                    .map(department->department.getLocation().getCountry().getRegion())
                        .findAny().orElse(null);
        //return new Region();
    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getCountry()
                        .getRegion().getRegionName().equals("Europe"))
                            .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display if there is any employee with salary less than 1000.
    // If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        return employeeService.readAll().stream()
                // stream of employees
                //.map(employee -> employee.getSalary() >=1000).findAny().isPresent();
                //.anyMatch(employee -> employee.getSalary() >=1000);
                .allMatch(employee -> employee.getSalary() >=1000) ;
        // find if there is
        //return false;

    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                //.allMatch(employee -> employee.getSalary()>2000);
                .noneMatch (employee -> employee.getSalary()<=2000);
        //return false;

    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()<5000)
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()>6000 && employee.getSalary()<7000)
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Douglas")
                        && employee.getLastName().equals("Grant"))
                //.map(Employee::getSalary).reduce(0L, Long::sum); // works also
                .map(Employee::getSalary).reduce(0L, (a,b)->b);
                //.findAny().get().getSalary();

        //return 1L;
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        return employeeService.readAll().stream()
                .map(Employee::getSalary) // all salaries
                .reduce(0L,Math::max); // need just max/one salary: reduce
        //return 1L;
    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {

        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getMaxSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());

    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {

        return getMaxSalaryEmployee().stream()
                .map(Employee::getJob)
                .findAny().orElseThrow();
        //return new Job();

    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .map(Employee::getSalary)
                .reduce(0L,Math::max);
        //return 1L;

    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        Long maxSalary=employeeService.readAll().stream()
                .map(Employee::getSalary) // all salaries
                .reduce(0L,Math::max); // need just max/one salary: reduce
//        return employeeService.readAll().stream()
//                .map(Employee::getSalary)
//                .filter(salary -> salary <maxSalary)
//                .reduce(0L,Math::max);

        return employeeService.readAll().stream()
                .map(Employee::getSalary).reduce((a,b)-> {
                    try {
                        return a>b && a< getMaxSalary()?a:b;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0L;
                }).orElse(null);

        //return 1L;
    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {

        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getSecondMaxSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());

        //return new ArrayList<>();
    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary()  {

//        return employeeService.readAll().stream()
//                .map(Employee::getSalary) // all salaries
//                //.reduce(Math::min).get(); // works also
//                .reduce(Math::min).orElse(null);

        return employeeService.readAll().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow().getSalary();
        //return 1L;
    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {

        return employeeService.readAll().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getMinSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .stream().collect(Collectors.toList());

        //return new ArrayList<>();
    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() {

        return employeeService.readAll().stream()
                .map(Employee::getSalary).sorted().distinct()
                .limit(2).collect(Collectors.toList()).get(1);

       // return 1L;
    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().equals(getSecondMinSalary());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        return employeeService.readAll().stream()
                .mapToDouble(Employee::getSalary)// not simple map !!!!
                .average().orElse(0d);
        //return 1d;
    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()>getAverageSalary())
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()<getAverageSalary())
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        return employeeService.readAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getId()));
        //return new HashMap<>();
    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        return departmentService.readAll().stream()
                .mapToDouble(Department::getId).count();
       // return 1L;
    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Alyssa"))
                .filter(employee -> employee.getManager().getFirstName().equals("Eleni"))
                .findAny().orElse(null);
        /*
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Alyssa") && employee.getManager().getFirstName().equals("Eleni"))
                .findAny().orElse(null);

         */

        //return new Employee();
    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                    .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getStartDate().isAfter( LocalDate.of(2005,1,1)))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getEndDate().isEqual( LocalDate.of(2007,12,31)))
                .filter(jobHistory -> jobHistory.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
         jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getStartDate().isEqual( LocalDate.of(2007,1,1)))
                .filter(jobHistory -> jobHistory.getEndDate().isEqual( LocalDate.of(2007,12,31)))
                .filter(jobHistory -> jobHistory.getDepartment().getDepartmentName().equals("Shipping"));

               return jobHistoryService.readAll().stream()
                       .filter(employee -> employee.getStartDate().isEqual( LocalDate.of(2007,1,1)))
                       .filter(employee -> employee.getEndDate().isEqual( LocalDate.of(2007,12,31)))
                       .filter(employee -> employee.getDepartment().getDepartmentName().equals("Shipping"))
                       .findAny().get().getEmployee();

        //return new Employee();
    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().contains("IT"))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        return (long) (int) employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .filter(employee -> employee.getJob().getJobTitle().equals("Programmer")).count();
        //return 1L;
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        List<Long> list=Arrays.asList(50L, 80L, 100L);
        return employeeService.readAll().stream()
                .filter(employee -> list.contains(employee.getDepartment().getId()))
                    .collect(Collectors.toList());

        //return new ArrayList<>();
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {

        return employeeService.readAll().stream()
                .map(employee -> ""+employee.getFirstName().charAt(0) + employee.getLastName().charAt(0))
                .collect(Collectors.toList());

        //return new ArrayList<>();
    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        return employeeService.readAll().stream()
                .map(employee -> employee.getFirstName() +" "+ employee.getLastName())
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        return employeeService.readAll().stream()
                .mapToInt(employee -> (employee.getFirstName() +" "+ employee.getLastName()).length())
                .max().orElse(0);
        //return 1;
    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {

        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return (employee.getFirstName().length()+employee.getLastName().length()+1)==getLongestNameLength();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toList());



        //return new ArrayList<>();
    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        List<Long> list=Arrays.asList(90L, 60L, 100L, 120L,130L);
        return employeeService.readAll().stream()
                .filter(employee -> list.contains(employee.getDepartment().getId()))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        List<Long> list=Arrays.asList(90L, 60L, 100L, 120L,130L);
        return employeeService.readAll().stream()
                .filter(employee -> !list.contains(employee.getDepartment().getId()))
                .collect(Collectors.toList());
        //return new ArrayList<>();
    }

}

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class JobBoard {

  public static void main(String[] args) throws IOException {

    // Read content from JSON file into list of hashmaps
    ClassLoader classLoader = JobBoard.class.getClassLoader();
    File file = new File(classLoader.getResource("positions.json").getFile());
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    List<HashMap<String, String>> positionList;
    positionList = mapper.readValue(file, ArrayList.class);

    // Populate the list of hashmaps into list of Job objects
    List<Job> jobList = new ArrayList<>();
    for(HashMap<String, String> position : positionList) {
      Job job = new Job();
      job.setId(position.get("id"));
      job.setType(position.get("type"));
      job.setUrl(position.get("url"));
      job.setCreatedAt(position.get("created_at"));
      job.setCompany(position.get("company"));
      job.setCompanyUrl(position.get("company_url"));
      job.setLocation(position.get("location"));
      job.setTitle(position.get("title"));
      job.setDescription(position.get("description"));
      jobList.add(job);
    }

    // Write getJobsByLocation method
    Map<String, Integer> jobsByLocationMap = new HashMap<>();
    jobsByLocationMap = Job.getJobsByLocation(jobList);

    Set<String> locations = jobsByLocationMap.keySet();
    System.out.println("\n-------\nNumber of Jobs by Location:");
    for(String location : locations) {
      System.out.println(location + ": " + jobsByLocationMap.get(location));
    }

    // Write getJobsByCompany method
    Map<String, Integer> jobsByCompanyMap = new HashMap<>();
    jobsByCompanyMap = Job.getJobsByCompany(jobList);
    Set<String> companies = jobsByCompanyMap.keySet();
    System.out.println("\n-------\nNumber of Jobs by Company:");
    for(String company : companies) {
      System.out.println(company + ": " + jobsByCompanyMap.get(company));
    }

    Scanner inputScanner = new Scanner(System.in);
    System.out.println("Would you like to submit a job? y/n:");
    String submitJob = inputScanner.nextLine();
    if(submitJob.equals("y")) {
      System.out.println("Enter the following information for the new job:");
      System.out.println("Type:");
      String type = inputScanner.nextLine();
      System.out.println("URL:");
      String url = inputScanner.nextLine();
      System.out.println("Title:");
      String title = inputScanner.nextLine();
      System.out.println("Description:");
      String description = inputScanner.nextLine();
      Job newJob = new Job();
      newJob.setType(type);
      newJob.setUrl(url);
      newJob.setTitle(title);
      newJob.setDescription(description);

      ObjectWriter objectWriter = mapper.writer(new DefaultPrettyPrinter());
      objectWriter.writeValue(new File("job.json"), newJob);
    }
  }
}
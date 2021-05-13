import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Job {
    private String id;
    private String type;
    private String url;
    private String createdAt;
    private String company;
    private String companyUrl;
    private String location;
    private String title;
    private String description;

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getCompany() {
    return company;
  }

  public String getCompanyUrl() {
    return companyUrl;
  }

  public String getLocation() {
    return location;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setCompanyUrl(String companyUrl) {
    this.companyUrl = companyUrl;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static Map<String, Integer> getJobsByLocation(List<Job> jobList) {
    Map<String, Integer> jobsByLocationMap = new HashMap<>();
    String location;
    int numJobs;
    for(Job job : jobList) {
      location = job.getLocation();
      if(jobsByLocationMap.containsKey(location)) {
        numJobs = jobsByLocationMap.get(location) + 1;
        jobsByLocationMap.replace(location, numJobs);
      } else {
        numJobs = 1;
        jobsByLocationMap.put(location, numJobs);
      }
    }
    return jobsByLocationMap;
  }

  public static Map<String, Integer> getJobsByCompany(List<Job> jobList) {
    Map<String, Integer> jobsByCompanyMap = new HashMap<>();
    String company;
    int numJobs;
    for(Job job : jobList) {
      company = job.getCompany();
      if(jobsByCompanyMap.containsKey(company)) {
        numJobs = jobsByCompanyMap.get(company) + 1;
        jobsByCompanyMap.replace(company, numJobs);
      } else {
        numJobs = 1;
        jobsByCompanyMap.put(company, numJobs);
      }
    }
    return jobsByCompanyMap;
  }


}

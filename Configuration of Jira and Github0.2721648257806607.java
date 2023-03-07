
@RestController
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping("/configure")
    public void configure(@RequestParam String serviceName, @RequestParam String jiraCredentials, @RequestParam String githubCredentials) {
        configurationService.configure(serviceName, jiraCredentials, githubCredentials);
    }
}

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public void configure(String serviceName, String jiraCredentials, String githubCredentials) {
        Configuration configuration = new Configuration(serviceName, jiraCredentials, githubCredentials);
        configurationRepository.save(configuration);
    }
}

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Long> {

}

@Entity
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serviceName;
    private String jiraCredentials;
    private String githubCredentials;

    public Configuration(String serviceName, String jiraCredentials, String githubCredentials) {
        this.serviceName = serviceName;
        this.jiraCredentials = jiraCredentials;
        this.githubCredentials = githubCredentials;
    }

    // getters, setters
}
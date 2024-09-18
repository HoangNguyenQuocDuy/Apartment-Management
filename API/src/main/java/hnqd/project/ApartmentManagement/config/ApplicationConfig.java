package hnqd.project.ApartmentManagement.config;

import com.cloudinary.Cloudinary;
import hnqd.project.ApartmentManagement.dto.InvoiceRequest;
import hnqd.project.ApartmentManagement.entity.Room;
import hnqd.project.ApartmentManagement.repository.IUserRepo;
import hnqd.project.ApartmentManagement.service.IInvoiceService;
import hnqd.project.ApartmentManagement.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class ApplicationConfig {
    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    @Value("${cloudinary.api-key}")
    private String apiKey;
    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Autowired
    private IRoomService roomService;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IInvoiceService invoiceService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", true);

        return new Cloudinary(config);
    }

    @Scheduled(cron = "0 10 15 17 * ?")
    public void createInvoices() {
        Map<String, String> paramsRoom = new HashMap<>();
        paramsRoom.put("status", "Rented");
        List<Room> rooms = roomService.getRooms(paramsRoom);

        rooms.forEach(room -> {
            InvoiceRequest invoice = InvoiceRequest.builder()
                    .invoiceTypeId(3)
                    .amount(room.getRoomType().getPrice())
                    .roomId(room.getId())
                    .description("Invoice rent")
                    .build();

            invoiceService.createInvoice(invoice);
        });
    }
}

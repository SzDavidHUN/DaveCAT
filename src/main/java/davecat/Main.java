package davecat;

import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.CourseRepository;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.DayOfWeek;

@SpringBootApplication
@EnableJpaRepositories
public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) {

    }

    private void initDB(){
        courseRepository.save(
                new Course(
                        /*new UUID(0, 0),*/
                        "Operációs Rendszerek - 1",
                        "Oprációs Rendszerek című tárgy gyakorlata, első csoport, Csütörtök 10-12",
                        "IN/102",
                        "Szerda 10-12",
                        14,
                        DayOfWeek.WEDNESDAY,
                        10,
                        12
                )
        );
        courseRepository.save(
                new Course(
                        /*new UUID(0,1),*/
                        "Számítógép Architektúrák",
                        "A tárgy gyakorlata, amelyen főképp a Linux alap rendszerekkel és hozzájuk kapcsolódó technológiákkal ismerkedhetünk meg",
                        "IN/103",
                        "Csütörtök 12-14",
                        14,
                        DayOfWeek.THURSDAY,
                        12,
                        14
                )
        );
        courseRepository.save(
                new Course(
                        /*new UUID(0,2),*/
                        "Kvarc Óra",
                        "Lorem ipszum",
                        "/dev/null",
                        "Hétfőn 11 órakor",
                        14,
                        DayOfWeek.MONDAY,
                        11,
                        12
                )
        );

        userRepository.save(
                new User(
                        "Szabó Dávid",
                        "T9D0K1",
                        "davidka1997@gmail.com",
                        "nincs",
                        User.Role.ADMIN
                )
        );
        userRepository.save(
                new User(
                        "Kis Pista",
                        "K5PSTA",
                        "kispista@uni-nowhere.co",
                        "keine",
                        User.Role.STUDENT
                )
        );
    }
}

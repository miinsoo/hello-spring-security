package kr.ac.hansung;

import kr.ac.hansung.entity.Product;
import kr.ac.hansung.entity.Role;
import kr.ac.hansung.entity.User;
import kr.ac.hansung.repository.ProductRepository;
import kr.ac.hansung.repository.RoleRepository;
import kr.ac.hansung.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        if (!userRepository.existsByEmail("admin@hansung.ac.kr")) {
            User admin = new User();
            admin.setEmail("admin@hansung.ac.kr");
            admin.setPassword(passwordEncoder.encode("admin1234"));
            admin.getRoles().add(userRole);
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
            log.info("초기 관리자 계정 생성: admin@hansung.ac.kr / admin1234");
        }

        if (productRepository.count() == 0) {
            productRepository.save(new Product("Spring Boot 4 완벽 가이드", 35000, "Spring Boot 4 + JPA + Security 실습서", 50));
            productRepository.save(new Product("Spring Security 7 핵심 원리", 28000, "세션·JWT·OAuth2 기반 보안 구현", 30));
            productRepository.save(new Product("JPA 프로그래밍 실전", 32000, "Hibernate 7 기반 ORM 마스터", 25));
            productRepository.save(new Product("Thymeleaf 완전 정복", 22000, "서버사이드 템플릿 엔진 가이드", 40));
            productRepository.save(new Product("React 입문", 27000, "리액트 18 기초부터 실전까지", 0));
            productRepository.save(new Product("Vue.js 3 완전 정복", 31000, "Composition API 기반 프론트엔드 개발", 15));
            productRepository.save(new Product("Docker & Kubernetes 실전", 38000, "컨테이너 오케스트레이션 마스터", 20));
            productRepository.save(new Product("파이썬 머신러닝 완벽 가이드", 42000, "scikit-learn + 딥러닝 입문", 35));
            productRepository.save(new Product("Git & GitHub 마스터", 19000, "버전 관리 및 협업 워크플로우", 60));
            productRepository.save(new Product("클린 코드", 33000, "애자일 소프트웨어 장인 정신", 45));
            productRepository.save(new Product("삼성전자 갤럭시 S25", 1200000, "삼성전자 최신 플래그십 스마트폰", 30));
            productRepository.save(new Product("삼성전자 갤럭시 탭 S10", 950000, "삼성전자 프리미엄 안드로이드 태블릿", 20));
            productRepository.save(new Product("삼성전자 갤럭시 워치 7", 350000, "삼성전자 스마트워치 최신형", 25));
            productRepository.save(new Product("애플 맥북 프로 M4", 3200000, "M4 칩 탑재 고성능 노트북", 0));
            productRepository.save(new Product("애플 아이패드 에어 M2", 900000, "M2 칩 탑재 프리미엄 태블릿", 15));
            productRepository.save(new Product("LG 그램 17", 1800000, "초경량 고성능 노트북 LG 그램", 10));
            productRepository.save(new Product("소니 WH-1000XM5", 420000, "소니 프리미엄 노이즈캔슬링 헤드폰", 40));
            productRepository.save(new Product("로지텍 MX Master 3S", 130000, "로지텍 프리미엄 무선 마우스", 55));
            productRepository.save(new Product("닌텐도 스위치 2", 550000, "닌텐도 최신 게임 콘솔", 0));
            productRepository.save(new Product("LG 27인치 4K 모니터", 680000, "LG UltraFine 4K IPS 디스플레이", 18));
            log.info("샘플 상품 20건 생성 완료");
        }
    }
}

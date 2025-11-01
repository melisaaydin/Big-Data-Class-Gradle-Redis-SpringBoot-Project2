Büyük Veri Projesi 2 (Big Data Project 2)
Proje Açıklaması

Bu proje, büyük veri işleme ve yönetimi için tasarlanmış bir Spring Boot uygulamasıdır.
Veri depolama, işleme ve web tabanlı etkileşim için çeşitli teknolojileri entegre ederek büyük veri kümelerini yönetmeye yönelik sağlam bir çözüm sunar.

Kullanılan Teknolojiler

Spring Boot – Uygulamanın geliştirilmesinde kullanılan framework.

Java 11 – Programlama dili.

MySQL – Kalıcı veri depolama için ilişkisel veritabanı.

Spring Data JPA – Veritabanı işlemleri için kullanılan ORM aracı.

Redis – Bellek içi veri deposu; önbellekleme veya hızlı veri erişimi için kullanılır.

Apache Hadoop (HDFS) – Büyük veri kümelerinin dağıtık depolanması ve yönetimi.

Lombok – Tekrarlayan kod yazımını azaltmak için kullanılır.

Kurulum Adımları

Depoyu klonlayın:

git clone [<repository_url>](https://github.com/melisaaydin/Big-Data-Class-Gradle-Redis-SpringBoot-Project2.git)
cd bigdata-project2


Veritabanı Kurulumu:

MySQL’in yüklü ve çalışır durumda olduğundan emin olun.

bigdata_project2 adında bir veritabanı oluşturun (veya application.properties dosyasındaki ayara göre).

src/main/resources/application.properties dosyasına kendi MySQL kullanıcı adı ve şifrenizi girin.

Redis Kurulumu:

Redis’in yüklü ve çalışır durumda olduğundan emin olun.

Hadoop Kurulumu (lokalde çalıştırılacaksa):

Apache Hadoop’un sisteminizde doğru şekilde yapılandırıldığından emin olun.

Proje, Hadoop HDFS üzerinden veri okuma/yazma işlemleri yapacak şekilde tasarlanmıştır.

Projeyi derleyin:

./gradlew clean build

Nasıl Çalıştırılır

Spring Boot uygulamasını başlatın:

java -jar build/libs/bigdata-project2-0.0.1-SNAPSHOT.jar


Alternatif olarak, Gradle ile doğrudan çalıştırabilirsiniz:

./gradlew bootRun


Uygulama varsayılan olarak http://localhost:8080 adresinde çalışacaktır.

🏗️ Proje Mimarisi

Proje, katmanlı mimari (layered architecture) prensibine göre tasarlanmıştır.
Uygulamanın veri akışı ve bileşenleri aşağıdaki gibidir:

🔹 1. Controller Katmanı

Kullanıcı isteklerini (HTTP request) alır ve yanıtları döner.
Spring Boot’un @RestController anotasyonu ile oluşturulan sınıflar, servis katmanına yönlendirme yapar.
Örnek: EmployeeController.java

Yeni çalışan ekleme

Çalışanları listeleme

Hadoop veya Redis işlemlerini başlatma

🔹 2. Service Katmanı

Uygulamanın iş mantığı (business logic) burada bulunur.
Controller’dan gelen istekler işlenir, doğrulamalar yapılır ve veri işlemleri gerçekleştirilir.
Örnek: EmployeeService.java

Veritabanı sorguları

Redis önbellek kontrolü

Hadoop dosya yazma/okuma işlemleri

🔹 3. Repository Katmanı

Spring Data JPA ile MySQL veritabanı işlemleri yapılır.
CRUD (Ekle, Listele, Güncelle, Sil) işlemleri bu katmanda gerçekleştirilir.
Örnek: EmployeeRepository.java

🔹 4. Veri Katmanı (Data Layer)

Proje birden fazla veri deposu kullanır:

MySQL: Uygulamanın kalıcı verilerini saklar.

Redis: Sık erişilen veriler için önbellekleme sağlar, performansı artırır.

Hadoop (HDFS): Büyük veri kümelerini dağıtık olarak depolar.

🔹 5. Veri Akışı

Kullanıcı REST API üzerinden bir istek gönderir.

Controller, isteği Service katmanına yönlendirir.

Service, önce Redis’te veri olup olmadığını kontrol eder.

Varsa, veri önbellekten alınır.

Yoksa MySQL veya Hadoop üzerinden alınarak Redis’e kaydedilir.

Sonuç JSON formatında istemciye döner.

🔹 6. Ek Bileşenler

Lombok: Getter, Setter, Constructor gibi tekrar eden kodları azaltır.

Gradle: Projenin derlenmesi ve bağımlılık yönetimi için kullanılır.

Spring Boot DevTools (isteğe bağlı): Geliştirme sırasında otomatik yeniden başlatma sağlar.

Bu mimari, uygulamanın yüksek performanslı, ölçeklenebilir ve sürdürülebilir olmasını sağlar.
Redis ile hız, MySQL ile kalıcılık, Hadoop ile büyük veri depolama dengeli bir şekilde kullanılmıştır.

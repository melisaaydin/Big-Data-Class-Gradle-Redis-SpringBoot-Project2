# 🧠 Büyük Veri Projesi 2 (Big Data Project 2)

## 📌 Proje Açıklaması

Bu proje, **gerçek zamanlı veri işleme ve büyük veri yönetimi** için tasarlanmış bir **Spring Boot uygulamasıdır**.  
Amaç, **bellek içi önbellekleme (Redis)** ile hız sağlarken, **Hadoop HDFS** üzerinde belgelerin (örneğin personel resimleri) güvenli ve dağıtık olarak saklanmasını sağlamaktır.  
Ayrıca **MySQL (Scott Database)** üzerinden kalıcı veri yönetimi yapılır.  

Bu yapı sayesinde sistem:
- Gerçek zamanlı veri erişimini hızlandırır,  
- Redis ile önbellek mekanizması kullanarak performansı artırır,  
- HDFS ile yüksek hacimli verileri güvenli biçimde depolar.  

---

## ⚙️ Kullanılan Teknolojiler

- **Spring Boot** – Ana uygulama çatısı.  
- **Java 11** – Programlama dili.  
- **MySQL (Scott Database)** – Kalıcı veri depolama.  
- **Spring Data JPA** – Veritabanı etkileşimi.  
- **Redis** – Gerçek zamanlı veri önbellekleme.  
- **Apache Hadoop (HDFS)** – Personel resimlerinin dağıtık dosya sisteminde saklanması.  
- **Lombok** – Kod tekrarını azaltmak için.  
- **Gradle** – Derleme ve bağımlılık yönetimi aracı.

---

## 🗃️ Veritabanı (Scott Database)

Proje, [Scott Database](https://github.com/rsp/pg-scott) veritabanını kullanır.  
Bu veritabanında **Employee** ve **Department** tabloları bulunmaktadır.

**Kullanılan temel alanlar:**
- `Employee`: `empno`, `ename`, `mgr`, `sal`, `comm`, `deptno`  
- `Department`: `deptno`, `dname`, `loc`

**JOIN Sonucu Görüntülenecek Bilgiler:**
| Çalışan Adı | Yönetici Adı | Maaş | Komisyon | Departman |
|--------------|---------------|-------|------------|-------------|
| Smith | King | 2800 | 300 | Sales |

---

## 🧩 Proje Mimarisi

Proje, **katmanlı mimari (layered architecture)** prensibine göre tasarlanmıştır.  
Aşağıdaki bileşenlerden oluşur:

### 🔹 1. Controller Katmanı
- Kullanıcıdan gelen HTTP isteklerini alır.  
- `@RestController` anotasyonu ile tanımlanır.  
- Servis katmanına yönlendirir.  
**Örnek:**  
- `EmployeeController.java`  
  - Çalışanları listeleme  
  - Yeni çalışan ekleme  
  - HDFS’e resim yükleme veya resim getirme  

### 🔹 2. Service Katmanı
- Uygulamanın iş mantığını içerir.  
- Redis önbelleğini yönetir ve gerekirse MySQL’e flush işlemi yapar.  
**Örnek:**  
- `EmployeeService.java`  
  - Redis’ten veri okur, yoksa MySQL’den çeker.  
  - Redis dolduğunda veya belirli aralıklarla veriyi MySQL’e aktarır.  
  - HDFS üzerinde resim dosyalarını kaydeder veya okur.

### 🔹 3. Repository Katmanı
- `Spring Data JPA` kullanarak MySQL üzerinde CRUD işlemlerini gerçekleştirir.  
- Örnek: `EmployeeRepository.java`, `DepartmentRepository.java`

### 🔹 4. Veri Katmanı (Data Layer)
Projede üç ana veri deposu vardır:
- **MySQL:** Kalıcı veriler.  
- **Redis:** Gerçek zamanlı önbellek.  
- **HDFS:** Personel resimlerinin veya belgelerin saklandığı dağıtık dosya sistemi.

### 🔹 5. Veri Akışı

1. Kullanıcı web arayüzünden çalışan listesini görüntüler.  
2. `Controller` isteği `Service` katmanına iletir.  
3. `Service`, Redis önbelleğinde veri olup olmadığını kontrol eder.  
   - **Varsa:** Veri Redis’ten döner (çok hızlı).  
   - **Yoksa:** MySQL’den alınır, Redis’e yazılır.  
4. Kullanıcı yeni bir çalışan eklediğinde, veriler önce Redis’e yazılır,  
   belirli aralıklarla MySQL’e **flush** edilir.  
5. Personel resimleri yüklenirse, dosyalar **HDFS** üzerinde saklanır ve aynı sistemden okunur.  

---

## 🧰 Kurulum Adımları

1. **Depoyu klonlayın:**
   ```bash
   git clone https://github.com/melisaaydin/Big-Data-Class-Gradle-Redis-SpringBoot-Project2.git
   cd bigdata-project2
   ```

2. **MySQL Kurulumu:**
   - MySQL’i başlatın.  
   - `bigdata_project2` adında bir veritabanı oluşturun.  
   - Scott verilerini bu veritabanına aktarın.  
   - `src/main/resources/application.properties` dosyasını düzenleyin:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/bigdata_project2
     spring.datasource.username=root
     spring.datasource.password=your_password
     ```

3. **Redis Kurulumu:**
   - Redis’i başlatın:  
     ```bash
     redis-server
     ```

4. **Hadoop HDFS (Tek Node) Kurulumu:**
   - Hadoop NameNode ve DataNode servislerini başlatın:  
     ```bash
     start-dfs.sh
     ```
   - HDFS üzerinde resimler için bir dizin oluşturun:  
     ```bash
     hdfs dfs -mkdir /employee_images
     ```

5. **Projeyi derleyin ve çalıştırın:**
   ```bash
   ./gradlew clean build
   ./gradlew bootRun
   ```

6. Uygulama `http://localhost:8080` adresinde çalışacaktır.



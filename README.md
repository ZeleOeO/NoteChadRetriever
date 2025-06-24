# NoteChadRetriever

---

An API I built to retriever from the [NoteChad](https://github.com/ZeleOeO/NoteChad) Project I worked on with JWT authentication.

## Technologies

---

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue?logo=apachemaven&logoColor=white)

## Features

---

- Retrieves all the authors
- Automatically regenerates jwt if it's expired

## Prerequisites

---

- Java 17+
- **Optional** - API Client (Postman, Yaak, Insominia etc.). You can always use curl
- **Optional** - An IDE such as IntelliJ, VSCode, Netbeans

## Installation (With Git)

---

1. Clone the repository:
   ```bash
   git clone ssh://git@codeberg.org/zele/NoteChadRetriever.git
   ```
2. Navigate to the project directory:
   ```bash
   cd NoteChadRetriever
   ```   
## Running

---

Run with:

```shell
mvn spring-boot:run
```

## Usage

---

I don't have docs rn, I have a whole stockpile of [abandoned projects](https://github.com/ZeleOeO?tab=repositories) that
I have to attend to, so maybe one day i'll come back.

## Testing

---

For now, don't have tests, but if i did

```shell
mvn test
```

## Steps to Contribute

---

Contributions are more than welcome, I'm still working on it though, so... keep that in mind or something.

1. Open an issue first so I can like keep track, but if that's too much stress that's fine too
2. Fork the Repository
3. Clone your fork
4. Create a new branch
   ```bash
   git checkout -b your-branch-name
   ```
5. Make your change
6. Commit your change, please
   use [Conventional Commits](https://gist.github.com/qoomon/5dfcdf8eec66a051ecd85625518cfd13) if you can, I didn't
   really use it here tbh
7. Push your change
8. Make a pull request and reference your issue <br>
   Please stick to conventional methods of programming java and springboot applications, don't mess up my already
   spaghetti code
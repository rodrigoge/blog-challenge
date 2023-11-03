
# Blog Challenge

A challenge for developer a minimal blog with posts and commentaries using Clean Architecture, Spring and React.js

## Tools

**Back-end:** 
- Java 21
- Spring Boot 3
- Flyway Migration
- PostgreSQL | H2 Database
- JUnit

## API Documentation

#### Post endpoints

```http
  POST   /api/posts
  GET    /api/posts
  PUT    /api/posts/{postId}
  DELETE /api/posts/{postId}
```

#### Commentary endpoints

```http
  POST   /api/commentaries/{postId}
  GET    /api/commentaries/{postId}
  PUT    /api/commentaries/{commentaryId}/{postId}
  DELETE /api/commentaries/{commentaryId}/{postId}
```

## License
[MIT](https://github.com/rodrigoge/blog-challenge/blob/main/LICENSE)

## Author
- Develope by ❤️ [@rodrigoge](https://www.github.com/rodrigoge)

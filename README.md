# Help Desk System - Spring Boot

Proyecto base de Spring Boot para implementar un sistema de Help Desk aplicando patrones de diseño.

## 🚀 Características

- Spring Boot 3.2.0
- Java 17
- Maven como gestor de dependencias
- Spring Web para APIs REST
- Configuración de CORS
- Manejo global de excepciones
- Estructura preparada para implementar patrones de diseño

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── DemoApplication.java          # Clase principal
│   │       ├── config/                       # Configuraciones
│   │       │   └── WebConfig.java
│   │       ├── controller/                   # Controladores REST
│   │       │   └── HelpDeskController.java
│   │       ├── service/                      # Lógica de negocio
│   │       │   └── HelpDeskService.java
│   │       └── exception/                    # Manejo de excepciones
│   │           └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties           # Configuración
└── test/
    └── java/
        └── com/example/demo/
            └── DemoApplicationTests.java
```

## 🎯 Objetivo

Implementar los siguientes patrones de diseño para resolver los problemas del sistema Help Desk:

1. **Strategy Pattern**: Para el cálculo de SLA según el tipo de cliente
2. **Chain of Responsibility**: Para la asignación de tickets según la severidad
3. **Observer Pattern**: Para las notificaciones (Email, Slack, Billing)

## 🛠️ Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior

## 📦 Instalación y Ejecución

### 1. Compilar el proyecto

```bash
mvn clean install
```

### 2. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O ejecutar directamente la clase `DemoApplication` desde tu IDE.

### 3. Verificar que funciona

Abre tu navegador y visita:
- http://localhost:8080/api/health

Deberías ver el mensaje: "Help Desk Service está funcionando correctamente."

## 📝 Próximos Pasos

1. **Crear modelos**: Implementa las clases `Ticket` y `Severity` en el paquete `model/`
2. **Implementar Strategy**: Crea estrategias de SLA en el paquete `strategy/`
3. **Implementar Chain of Responsibility**: Crea handlers para asignación de tickets en el paquete `chain/`
4. **Implementar Observer**: Crea observadores para notificaciones en el paquete `observer/`
5. **Implementar servicio**: Completa `HelpDeskService` usando los patrones
6. **Crear endpoints**: Agrega endpoints REST en `HelpDeskController`

## 🔧 Configuración

Puedes modificar la configuración en `src/main/resources/application.properties`:

- Puerto del servidor: `server.port`
- Niveles de logging
- Y más...

## 📚 Dependencias Principales

- **spring-boot-starter-web**: Para crear APIs REST
- **spring-boot-starter-validation**: Para validación de datos
- **spring-boot-devtools**: Recarga automática durante desarrollo

## 🧪 Testing

Ejecuta los tests con:

```bash
mvn test
```

## 📄 Licencia

Este es un proyecto base educativo.


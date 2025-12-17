# Help Desk System - Spring Boot

Sistema de Help Desk implementado con Spring Boot que aplica patrones de diseño para resolver problemas de acoplamiento y rigidez en el código.

## 🚀 Características

- Spring Boot 3.2.0
- Java 17
- Maven como gestor de dependencias
- Spring Web para APIs REST
- Lombok para reducir boilerplate
- Implementación de 3 patrones de diseño: **Strategy**, **Chain of Responsibility** y **Observer**

## 📁 Estructura del Proyecto

```
src/main/java/com/mindhub/a4/
├── DemoApplication.java              # Clase principal
├── clasess/
│   ├── Ticket.java                   # Modelo de ticket
│   ├── StandardCustomer.java         # Estrategia para clientes estándar
│   ├── PremiumCustomer.java          # Estrategia para clientes premium
│   ├── EnterpriseCustomer.java       # Estrategia para clientes enterprise
│   ├── TicketSubject.java            # Subject del patrón Observer
│   ├── EmailObserver.java            # Observador para notificaciones por email
│   ├── SlackObserver.java            # Observador para notificaciones por Slack
│   └── BillingObserver.java          # Observador para facturación
├── handler/                          # Chain of Responsibility
│   ├── SupportHandler.java           # Handler abstracto
│   ├── Level1SupportHandler.java    # Handler para severidad BASIC
│   ├── Level2SupportHandler.java    # Handler para severidad MEDIUM
│   ├── ManagerSupportHandler.java    # Handler para severidad CRITICAL
│   └── UnresolverSupportHandler.java # Handler final para tickets no resueltos
├── interfaces/
│   ├── ICustomerStrategy.java        # Interfaz para el patrón Strategy
│   └── ITicketObserver.java         # Interfaz para el patrón Observer
├── services/
│   └── HelpDeskService.java          # Servicio principal que orquesta los patrones
└── utils/
    └── Severity.java                 # Enum para severidad de tickets
```

## 🎯 Problemas Identificados y Soluciones

### Problema 1: Cálculo de SLA Rígido (Strategy Pattern)

#### ❌ Código Original (Problemático)
```java
int estimatedHours = 0;
if (ticket.customerType.equals("Premium")) {
    estimatedHours = 2;
} else if (ticket.customerType.equals("Enterprise")) {
    estimatedHours = 1;
} else {
    estimatedHours = 24;
}
```

**Problemas:**
- Lógica hardcodeada con múltiples `if-else`
- Difícil de extender (agregar nuevos tipos de cliente requiere modificar el código)
- Viola el principio Abierto/Cerrado
- Acoplamiento fuerte entre el servicio y los tipos de cliente

#### ✅ Solución con Strategy Pattern

**Implementación:**
- **Interfaz `ICustomerStrategy`**: Define el contrato para calcular SLA
- **Clases concretas**: `StandardCustomer`, `PremiumCustomer`, `EnterpriseCustomer`
- Cada estrategia encapsula su propia lógica de cálculo de SLA

**Ventajas:**
- ✅ Fácil agregar nuevos tipos de cliente sin modificar código existente
- ✅ Cada estrategia es independiente y testeable
- ✅ Cumple con el principio Abierto/Cerrado
- ✅ Código más limpio y mantenible

**Uso:**
```java
int estimatedHours = ticket.getCustomerStrategy().getSLA();
```

---

### Problema 2: Asignación de Tickets Hardcodeada (Chain of Responsibility)

#### ❌ Código Original (Problemático)
```java
boolean solved = false;

if (ticket.severity == Severity.BASIC) {
    System.out.println("Atendido por: Soporte Nivel 1");
    solved = true;
} 

if (!solved && ticket.severity == Severity.MEDIUM) {
    System.out.println("Atendido por: Soporte Nivel 2");
    solved = true;
}

if (!solved && ticket.severity == Severity.CRITICAL) {
    System.out.println("Atendido por: Gerente");
    solved = true;
}
```

**Problemas:**
- Múltiples `if-else` anidados
- Difícil agregar nuevos niveles de soporte
- Lógica de asignación acoplada al servicio principal
- No es flexible para cambiar el orden de procesamiento

#### ✅ Solución con Chain of Responsibility

**Implementación:**
- **`SupportHandler` (abstracto)**: Define la estructura base y el método para pasar al siguiente handler
- **Handlers concretos**: 
  - `Level1SupportHandler` → Maneja `BASIC`
  - `Level2SupportHandler` → Maneja `MEDIUM`
  - `ManagerSupportHandler` → Maneja `CRITICAL`
  - `UnresolverSupportHandler` → Maneja casos no resueltos
- **Cadena construida dinámicamente**: Cada handler puede pasar la solicitud al siguiente

**Ventajas:**
- ✅ Fácil agregar nuevos niveles de soporte sin modificar código existente
- ✅ Cada handler es independiente y tiene una responsabilidad única
- ✅ Orden de procesamiento configurable
- ✅ Código más limpio y extensible

**Uso:**
```java
// Construcción de la cadena
SupportHandler level1 = new Level1SupportHandler();
SupportHandler level2 = new Level2SupportHandler();
SupportHandler manager = new ManagerSupportHandler();

level1.setNextHandler(level2).setNextHandler(manager);

// Procesamiento
boolean solved = level1.handleRequest(ticket);
```

---

### Problema 3: Notificaciones Acopladas (Observer Pattern)

#### ❌ Código Original (Problemático)
```java
if (solved) {
    System.out.println("-- Notificando --");
    // Email Service
    System.out.println("Email: Enviando confirmación al cliente.");
    
    // Slack Service
    System.out.println("Slack: Nuevo ticket resuelto #" + ticket.id);
    
    // Billing Service (si aplica)
    if (ticket.customerType.equals("Premium") || ticket.customerType.equals("Enterprise")) {
        System.out.println("Billing: Registrando cargo por soporte.");
    }
}
```

**Problemas:**
- Notificaciones hardcodeadas en el método principal
- Difícil agregar nuevos canales de notificación
- Lógica condicional para Billing mezclada con otras notificaciones
- Alto acoplamiento entre el servicio y los sistemas de notificación

#### ✅ Solución con Observer Pattern

**Implementación:**
- **`ITicketObserver` (interfaz)**: Define el contrato para los observadores
- **`TicketSubject`**: Gestiona la lista de observadores y notifica cambios
- **Observadores concretos**:
  - `EmailObserver` → Notifica por email
  - `SlackObserver` → Notifica por Slack
  - `BillingObserver` → Maneja facturación (con lógica condicional interna)

**Ventajas:**
- ✅ Desacoplamiento total entre el servicio y los sistemas de notificación
- ✅ Fácil agregar nuevos observadores (SMS, WhatsApp, etc.) sin modificar código existente
- ✅ Cada observador maneja su propia lógica (incluyendo condiciones)
- ✅ Puede registrar/desregistrar observadores dinámicamente
- ✅ Cumple con el principio de Responsabilidad Única

**Uso:**
```java
// Registro de observadores
TicketSubject ticketSubject = new TicketSubject();
ticketSubject.addObserver(new EmailObserver());
ticketSubject.addObserver(new SlackObserver());
ticketSubject.addObserver(new BillingObserver());

// Notificación
if (solved) {
    ticketSubject.notifyObservers(ticket);
}
```

---

## 🛠️ Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior
- IDE con soporte para Lombok (IntelliJ IDEA, Eclipse, VS Code)

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

### 3. Salida Esperada

Al ejecutar la aplicación, deberías ver algo como:

```
Iniciando aplicacion Help Desk...
================================================
Aplicacion Help Desk iniciada correctamente
================================================

>>> Procesando Ticket #1 (BASIC)
StandardCustomer: SLA = 24, respuesta tardia
Tiempo estimado de resolución: 24 horas

>>> Asignando ticket #1 a un soporte...
Ticket #1 atendido por: Soporte Nivel 1 (Básico). Resuelto.

-- Notificando --
EmailObserver: Notificando al cliente por email que el ticket #1 ha sido resuelto.
SlackObserver: Notificando al equipo de soporte por Slack que el ticket #1 ha sido resuelto.
BillingObserver: Notificando al departamento de facturación que el ticket #1 ha sido resuelto.
Ticket #1 ha sido resuelto. Notificación enviada a todos los observadores.
```

## 🔧 Configuración

Puedes modificar la configuración en `src/main/resources/application.properties`:

- Puerto del servidor: `server.port=8080`
- Niveles de logging
- Configuración de la aplicación

## 📚 Dependencias Principales

- **spring-boot-starter-web**: Para crear APIs REST
- **spring-boot-starter-validation**: Para validación de datos
- **lombok**: Para reducir código boilerplate (getters, setters, constructores)
- **spring-boot-devtools**: Recarga automática durante desarrollo

## 🎓 Conceptos Aplicados

### Principios SOLID

1. **Single Responsibility**: Cada clase tiene una única responsabilidad
2. **Open/Closed**: Abierto para extensión, cerrado para modificación
3. **Liskov Substitution**: Las estrategias y handlers son intercambiables
4. **Interface Segregation**: Interfaces específicas y pequeñas
5. **Dependency Inversion**: Dependemos de abstracciones, no de implementaciones concretas

### Patrones de Diseño

1. **Strategy Pattern**: Para variar algoritmos (cálculo de SLA) en tiempo de ejecución
2. **Chain of Responsibility**: Para pasar solicitudes a través de una cadena de handlers
3. **Observer Pattern**: Para notificar a múltiples objetos sobre cambios de estado

## 📄 Licencia

Este es un proyecto educativo para demostrar la aplicación de patrones de diseño en Spring Boot.
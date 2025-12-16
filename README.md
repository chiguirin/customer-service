##Customer Service
Este microservicio se encarga de la gestión de clientes dentro del sistema. Centraliza la información básica del cliente y su estado, permitiendo que otros microservicios consulten si un cliente es válido para realizar operaciones.

Además de exponer endpoints REST para consulta y registro, aplica validaciones de negocio relacionadas con el estado del cliente, evitando operaciones cuando el cliente se encuentra inactivo o en una condición no permitida. Estas validaciones no se limitan al controlador, sino que forman parte de la lógica del servicio.

Funciona de manera independiente, con su propia base de datos y configuración, lo que facilita su despliegue y evolución sin afectar a otros componentes del sistema.

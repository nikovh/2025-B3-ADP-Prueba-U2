import { check, sleep, group } from 'k6';
import { Trend } from 'k6/metrics';

// métrica de tipo Tendencia para medir tiempos de respuesta.
const loginResponseTime = new Trend('login_response_time');

// CONFIGURACIÓN DE LA PRUEBA 
export const options = {
  // Perfil de Carga: Ramp-up de 30s a 10 VUs, luego 1 min de carga sostenida
  stages: [
    { duration: '30s', target: 10 }, // Aceleración
    { duration: '1m', target: 10 },  // Carga sostenida
  ],
  // Criterios de Aceptación (Thresholds)
  thresholds: {
    'login_response_time{escenario:login_exitoso}': ['p(95)<500'], // El percentil 95 debe ser < 500ms
    'checks{escenario:login_exitoso}': ['rate>0.99'], // La tasa de éxito de los checks debe ser > 99%
  },
};

// LÓGICA DE LA PRUEBA 
// función que cada Usuario Virtual (VU) ejecutará repetidamente.
export default function () {
  
  group('Flujo de Login de Usuario', function () {
    
    // --- Simulación del Trabajo ---
    // Como no tenemos un servicio HTTP real, simulamos el tiempo que tardaría 
    // la lógica de negocio en validar un usuario.
    // Tiempo de respuesta aleatorio entre 50ms y 250ms.
    const simulatedResponseTime = Math.random() * 200 + 50; 
    
    // "Pausa" para simular esa espera.
    sleep(simulatedResponseTime / 1000); // sleep() usa segundos, por eso se divide por 1000.

    // Recolección de Métricas
    // Añadimos el tiempo simulado a nuestra métrica personalizada.
    // El tag { escenario: 'login_exitoso' } nos permite filtrar los resultados.
    loginResponseTime.add(simulatedResponseTime, { escenario: 'login_exitoso' });

    // Verificación (Check)
    // Verificamos si la "respuesta" fue exitosa según nuestros criterios.
    check(simulatedResponseTime, {
      'Respuesta de login fue exitosa (menos de 500ms)': (t) => t < 500,
    }, { escenario: 'login_exitoso' });

  });

  // Pausa de 1 segundo entre iteraciones para simular el "tiempo de pensamiento" del usuario.
  sleep(1);
}
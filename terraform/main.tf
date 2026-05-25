# Configuración de Infraestructura como Código (IaC) para el proyecto Recuento de Gastos

terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 4.0"
    }
  }
}

provider "google" {
  project = "recuento-gastos-devsecops"
  region  = "us-central1"
}

# Definición del proyecto de Firebase para la App Móvil
resource "google_firebase_project" "auth" {
  provider = google-beta
  project  = "recuento-gastos-devsecops"
}

# Registro de la aplicación Android en la infraestructura
resource "google_firebase_android_app" "default" {
  provider     = google-beta
  project      = google_firebase_project.auth.project
  display_name = "Recuento Gastos Android App"
  package_name = "com.example.recuento"
}

# Evidencia de monitoreo: Habilitar Google Cloud Logging
resource "google_project_service" "logging" {
  project = "recuento-gastos-devsecops"
  service = "logging.googleapis.com"
}

# Script pour réorganiser le projet en séparant le backend et le frontend

# Création des dossiers pour le backend
$backendPath = "$PSScriptRoot\backend"
$frontendPath = "$PSScriptRoot\frontend"

# Supprimer les dossiers s'ils existent déjà
if (Test-Path $backendPath) { Remove-Item -Path $backendPath -Recurse -Force }
if (Test-Path $frontendPath) { Remove-Item -Path $frontendPath -Recurse -Force }

# Créer la structure du backend
$backendDirs = @(
    "$backendPath\src\main\java\org\example\controllers",
    "$backendPath\src\main\java\org\example\models",
    "$backendPath\src\main\java\org\example\utils",
    "$backendPath\src\main\resources"
)

foreach ($dir in $backendDirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Créer la structure du frontend
$frontendDirs = @(
    "$frontendPath\views",
    "$frontendPath\css",
    "$frontendPath\images"
)

foreach ($dir in $frontendDirs) {
    New-Item -ItemType Directory -Force -Path $dir | Out-Null
}

# Copier les fichiers Java vers le backend
Copy-Item -Path "$PSScriptRoot\src\main\java\org\example\*" -Destination "$backendPath\src\main\java\org\example\" -Recurse -Force

# Copier les fichiers de ressources (sauf views) vers le backend
Copy-Item -Path "$PSScriptRoot\src\main\resources\data" -Destination "$backendPath\src\main\resources\" -Recurse -Force

# Copier les fichiers FXML vers le frontend
Copy-Item -Path "$PSScriptRoot\src\main\resources\views\*" -Destination "$frontendPath\views\" -Recurse -Force

# Copier le fichier module-info.java
Copy-Item -Path "$PSScriptRoot\src\main\java\module-info.java" -Destination "$backendPath\src\main\java\" -Force

# Mettre à jour les chemins dans les contrôleurs
$controllers = Get-ChildItem -Path "$backendPath\src\main\java\org\example\controllers\*.java" -Recurse

foreach ($file in $controllers) {
    (Get-Content $file.FullName) | 
    ForEach-Object { $_ -replace '"/views/', '"../../../../frontend/views/' } | 
    Set-Content $file.FullName -Encoding UTF8
}

# Mettre à jour les chemins dans SceneManager
$sceneManagerPath = "$backendPath\src\main\java\org\example\utils\SceneManager.java"
if (Test-Path $sceneManagerPath) {
    (Get-Content $sceneManagerPath) | 
    ForEach-Object { $_ -replace '"/views/', '"../../../../frontend/views/' } | 
    Set-Content $sceneManagerPath -Encoding UTF8
}

Write-Host "Réorganisation terminée avec succès !" -ForegroundColor Green
Write-Host "Backend: $backendPath"
Write-Host "Frontend: $frontendPath"

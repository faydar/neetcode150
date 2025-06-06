param(
    [Parameter(Mandatory=$true)]
    [string]$problemName
)

$srcDir = "src/main/java"
$problemDir = "$srcDir/problems/$problemName"
$classPath = $srcDir

if (-not (Test-Path $problemDir)) {
    Write-Error "Problem klasörü bulunamadı: $problemDir"
    exit 1
}

Write-Host "Derleniyor..."

# Önce utils klasörünü derle (dependency olarak)
javac -cp $classPath (Get-ChildItem "$srcDir/utils" -Filter "*.java" -Recurse | ForEach-Object { $_.FullName })

# Sonra problem klasörünü derle
javac -cp $classPath "$problemDir/Solution.java"
if ($LASTEXITCODE -ne 0) {
    Write-Error "Derleme hatası!"
    exit 1
}

Write-Host "Derleme tamamlandı. Çalıştırılıyor..."

# Çalıştır (package yapısını koruyarak)
Get-Content "$problemDir/input.txt" | java -cp $classPath "problems.$problemName.Solution" > "$problemDir/output.txt"

Write-Host "Program çalıştırıldı. Sonuçlar: $problemDir/output.txt" 
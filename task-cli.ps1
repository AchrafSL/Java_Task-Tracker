#!/usr/bin/env pwsh

# Add two newlines for spacing
Write-Output ""

$jarPath = Join-Path $PSScriptRoot "task-cli.jar"
java -jar $jarPath @args

Write-Output ""

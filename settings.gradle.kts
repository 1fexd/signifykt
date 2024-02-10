rootProject.name = "signifykt"
include("lib")

if (System.getenv("DISABLE_TESTING_MODULE")?.toBooleanStrictOrNull() != false) {
    include("testing")
}

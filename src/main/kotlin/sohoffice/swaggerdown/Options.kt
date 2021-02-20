package sohoffice.swaggerdown

import picocli.CommandLine
import java.io.File

enum class Format {
    MD,
    HTML
}

enum class Flavor {
    STANDARD;

    val pathFragment = this.name.toLowerCase()
}

class Options {
    @CommandLine.Parameters(
        index = "0",
        paramLabel = "FILE",
        description = ["Swagger YAML files to convert"]
    )
    var file: File? = null

    @CommandLine.Option(names = ["-r", "--flavor"], description = ["The generation flavor. Values: \${COMPLETION-CANDIDATES}"], defaultValue = "STANDARD")
    var flavor: Flavor = Flavor.STANDARD

    @CommandLine.Option(names = ["-f", "--format"], description = ["The output format. Values: \${COMPLETION-CANDIDATES}"], defaultValue = "MD")
    var format: Format = Format.MD

    @CommandLine.Option(names = ["-h", "--help"], usageHelp = true, description = ["display a help message"], defaultValue = "false")
    var helpRequested: Boolean = false
}

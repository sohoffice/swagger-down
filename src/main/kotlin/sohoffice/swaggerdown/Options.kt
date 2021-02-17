package sohoffice.swaggerdown

import picocli.CommandLine
import java.io.File

class Options {
    @CommandLine.Parameters(
        index = "0",
        paramLabel = "FILE",
        description = ["Swagger YAML files to convert"]
    )
    var file: File? = null

    @CommandLine.Option(names = ["-r", "--flavor"], description = ["The generation flavor"], defaultValue = "standard")
    var flavor: String = "standard"

    @CommandLine.Option(names = ["-f", "--format"], description = ["The output format"], defaultValue = "md")
    var format: String = "md"

    @CommandLine.Option(names = ["-h", "--help"], usageHelp = true, description = ["display a help message"], defaultValue = "false")
    var helpRequested: Boolean = false
}

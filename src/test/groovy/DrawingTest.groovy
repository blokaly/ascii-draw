import spock.lang.Specification

class DrawingTest extends Specification {

    def 'Create Canvas Test'() {
        setup:
        def expect = """----------------------
                       `|                    |
                       `|                    |
                       `|                    |
                       `|                    |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Draw Horizontal Line left-to-right Test'() {
        setup:
        def expect = """----------------------
                       `|                    |
                       `|xxxxxx              |
                       `|                    |
                       `|                    |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("L", "1", "2", "6", "2");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Draw Horizontal Line right-to-left Test'() {
        setup:
        def expect = """----------------------
                       `|                    |
                       `|xxxxxx              |
                       `|                    |
                       `|                    |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "2", "1", "2");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Draw Vertical Line top-to-bottom Test'() {
        setup:
        def expect = """----------------------
                       `|                    |
                       `|     x              |
                       `|     x              |
                       `|     x              |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "2", "6", "4");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Draw Vertical Line bottom-to-top Test'() {
        setup:
        def expect = """----------------------
                       `|                    |
                       `|     x              |
                       `|     x              |
                       `|     x              |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "4", "6", "2");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Draw Dot Test'() {
        setup:
        def expect = """----------------------
                       `|                    |
                       `|                    |
                       `|                    |
                       `|     x              |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "4", "6", "4");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Draw Rectangle Test'() {
        setup:
        def expect = """----------------------
                       `|             xxxxx  |
                       `|             x   x  |
                       `|             xxxxx  |
                       `|                    |
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("R", "14", "1", "18", "3");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def 'Fill Area Test'() {
        setup:
        def expect = """----------------------
                       `|oooooooooooooxxxxxoo|
                       `|......ooooooox   xoo|
                       `|     .oooooooxxxxxoo|
                       `|     ........ooooooo|
                       `----------------------""".stripMargin('`').split("\\n");
        when:
        def cmd = new CmdLineProcessor();
        cmd.process("C", "20", "4");
        cmd.process("L", "1", "2", "6", "2");
        cmd.process("L", "6", "3", "6", "4");
        cmd.process("R", "14", "1", "18", "3");
        cmd.process("B", "10", "3", "o");
        cmd.process("L", "6", "4", "13", "4");
        cmd.process("B", "1", "2", ".");

        then:
        parsePixels(expect) == cmd.getCanvas();
    }

    def parsePixels(String[] lines) {
        char[][] pixels = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            pixels[i] = lines[i].toCharArray();
        }
        return new CanvasImpl(pixels);
    }
}

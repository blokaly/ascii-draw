import spock.lang.Specification

class ErrorHandlingTest extends Specification {

    def 'Create Canvas - throw IllegalArgumentException if not two parameters'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "1");

        then:
        thrown IllegalArgumentException
    }

    def 'Create Canvas - throw IllegalArgumentException if parameter not positive'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "1", "0");

        then:
        thrown IllegalArgumentException
    }

    def 'Draw Line - throw IllegalStateException if no canvas created'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("L", "6", "2", "6", "4");

        then:
        thrown IllegalStateException

    }

    def 'Draw Line - throw IllegalArgumentException if not four parameters'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "2", "6");

        then:
        thrown IllegalArgumentException
    }

    def 'Draw Line - throw IllegalArgumentException if parameter not positive'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "0", "6", "4");

        then:
        thrown IllegalArgumentException
    }

    def 'Draw Line - throw IllegalArgumentException if parameter out of canvas area'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "1", "6", "5");

        then:
        thrown IllegalArgumentException
    }

    def 'Draw Line - throw UnsupportedOperationException if not horizontal or vertical line'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("L", "6", "1", "10", "4");

        then:
        thrown UnsupportedOperationException
    }

    def 'Draw Rectangle - throw IllegalStateException if no canvas created'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("R", "14", "1", "18", "3");

        then:
        thrown IllegalStateException
    }

    def 'Draw Rectangle - throw IllegalArgumentException if not four parameters'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("R", "14", "1", "18");

        then:
        thrown IllegalArgumentException
    }

    def 'Draw Rectangle - throw IllegalArgumentException if parameter out of canvas area'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("R", "14", "1", "21", "5");

        then:
        thrown IllegalArgumentException
    }

    def 'Draw Rectangle - throw IllegalArgumentException if wrong coordinate positions'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("R", "14", "1", "10", "3");

        then:
        thrown IllegalArgumentException
    }

    def 'Fill Area - throw IllegalStateException if no canvas created'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("B", "10", "3", "o");

        then:
        thrown IllegalStateException
    }

    def 'Fill Area - throw IllegalArgumentException if not three parameters'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("B", "10", "3");

        then:
        thrown IllegalArgumentException
    }

    def 'Fill Area - throw IllegalArgumentException if parameter out of canvas area'() {
        setup:
        def cmd = new CmdLineProcessor();

        when:
        cmd.process("C", "20", "4");
        cmd.process("B", "10", "5", "o");

        then:
        thrown IllegalArgumentException
    }
}

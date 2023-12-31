package ldts.dino.viewer.menu

import ldts.dino.gui.GUI
import ldts.dino.model.menu.MainMenu
import ldts.dino.utils.Position
import ldts.dino.viewer.menu.MainMenuViewer
import spock.lang.Specification

class MainMenuViewerTest extends Specification {

    def "MainMenuViewer constructor"() {
        given:
        def menu = Mock(MainMenu)

        when:
        def viewer = new MainMenuViewer(menu)

        then:
        viewer != null
    }

    def "test drawEntries"() {
        given:
        def gui = Mock(GUI)
        def menu = new MainMenu()
        def viewer = new MainMenuViewer(menu)

        when:
        viewer.drawEntries(gui, 10, 20)

        then:
        menu.getNumberEntries() * gui.drawText(_,_,_,_)
    }
}
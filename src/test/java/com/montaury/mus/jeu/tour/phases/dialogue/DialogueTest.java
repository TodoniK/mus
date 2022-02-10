package com.montaury.mus.jeu.tour.phases.dialogue;

import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Equipe;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.Participants;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DialogueTest {

  @BeforeEach
  void setUp() {
    dialogue = new Dialogue(mock(Evenements.class));
  }

  @Test
  void engage_un_point_si_les_2_participants_sont_paso() {
    Joueur joueur1 = unJoueurFaisantChoix(new Paso());
    Joueur joueur2 = unJoueurFaisantChoix(new Paso());

    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(List.of(joueur1, joueur2)));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }

  @Test
  void est_termine_si_le_dernier_choix_est_tira() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Tira());
    Joueur joueurAdverse2 = unJoueurFaisantChoix(new Imido());
    Joueur joueurAllie = unJoueurFaisantChoix(new Tira());
    Joueur joueurZaku = unJoueurFaisantChoix(new Tira());
    var equipe1 = new Equipe(1,joueurEsku,joueurAllie);
    var equipe2 = new Equipe (2,joueurZaku,joueurAdverse2);
    var opposants = new Opposants(equipe1,equipe2);
    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(opposants.dansLOrdre()));

    assertThat(recapitulatif.pointsEngages()).isOne();
  }

  @Test
  void est_termine_si_le_dernier_choix_est_idoki() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Idoki());
    Joueur joueurAdverse2 = unJoueurFaisantChoix(new Imido());
    Joueur joueurAllie = unJoueurFaisantChoix(new Tira());
    Joueur joueurZaku = unJoueurFaisantChoix(new Tira());
    var equipe1 = new Equipe(1,joueurEsku,joueurAllie);
    var equipe2 = new Equipe (2,joueurZaku,joueurAdverse2);
    var opposants = new Opposants(equipe1,equipe2);
    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(opposants.dansLOrdre()));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(2);
  }

  @Test
  void est_termine_si_le_dernier_choix_est_kanta() {
    Joueur joueurEsku = unJoueurFaisantChoix(new Paso(), new Kanta());
    Joueur joueurAdverse2 = unJoueurFaisantChoix(new Hordago());
    Joueur joueurAllie = unJoueurFaisantChoix(new Tira());
    Joueur joueurZaku = unJoueurFaisantChoix(new Tira());
    var equipe1 = new Equipe(1,joueurEsku,joueurAllie);
    var equipe2 = new Equipe (2,joueurZaku,joueurAdverse2);
    var opposants = new Opposants(equipe1,equipe2);
    Dialogue.Recapitulatif recapitulatif = dialogue.derouler(new Participants(opposants.dansLOrdre()));

    assertThat(recapitulatif.pointsEngages()).isEqualTo(40);
  }
  private Dialogue dialogue;

}

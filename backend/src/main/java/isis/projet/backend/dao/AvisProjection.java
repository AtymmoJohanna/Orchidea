package isis.projet.backend.dao;

/**
 * Une "projection" de l'entité avis.
 * On conserve uniquement le nom de l'user
 * et le  commentaire de l'avis associée
 */
public interface AvisProjection {
    String getCommentaire();
    interface UserProjection {
        String getNom();
    }
    UserProjection getUser();
}
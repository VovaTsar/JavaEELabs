package JPA;


import controller.PlaneBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.util.List;

public class ManageDataBase {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private String persistenceUnitName="NewPersistenceUnit";

    public ManageDataBase() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public String updatePlaneDetails(int planeId, String updatedBrand, String updatedCaptain, String updatedEngine, String updatedSeries) {
        entityManager.getTransaction().begin();
        if (isPlaneIdPresent(planeId)) {
            Query queryObj = entityManager.createQuery("UPDATE PlaneEntity s SET s.brand=:brand, s.captain=:captain,s.engine=:engine,s.series=:series WHERE s.id= :id");
            queryObj.setParameter("id", planeId);
            queryObj.setParameter("brand", updatedBrand);
            queryObj.setParameter("captain", updatedCaptain);
            queryObj.setParameter("engine", updatedEngine);
            queryObj.setParameter("series", updatedSeries);
            int updateCount = queryObj.executeUpdate();
            if (updateCount > 0) {
                System.out.println("Refactor for Id: " + planeId + " Is Updated");
            }
        }
        entityManager.getTransaction().commit();
        FacesContext.getCurrentInstance().addMessage("editPlaneForm:planeId", new FacesMessage("Plane DB #" + planeId + " Is Successfully Updated In Db"));
        return "planeRefactor.xhtml";
    }

    public List<PlaneBean> getAll() {
        List<PlaneBean> listOfPlaneEntity = entityManager.createQuery("SELECT a FROM PlaneEntity a ").getResultList();
        return listOfPlaneEntity;
    }

    public String deletePlaneDetails(int planeId) {
        entityManager.getTransaction().begin();
        PlaneEntity deletePlaneObj = new PlaneEntity();
        if (isPlaneIdPresent(planeId)) {
            deletePlaneObj.setId(planeId);
            entityManager.remove(entityManager.merge(deletePlaneObj));
        }
        entityManager.getTransaction().commit();
        return "addList.xhtml?faces-redirect=true";
    }

    public boolean isPlaneIdPresent(int planeId) {
        boolean idResult = false;
        Query queryObj = entityManager.createQuery("SELECT s FROM PlaneEntity s WHERE s.id = :id");
        queryObj.setParameter("id", planeId);
        PlaneEntity selectedPlaneId = (PlaneEntity) queryObj.getSingleResult();
        if (selectedPlaneId != null) idResult = true;
        return idResult;
    }

    public void persistEntity(PlaneEntity planeEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(planeEntity);
        entityManager.getTransaction().commit();
    }

    public void deleteProduct(int id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from PlaneEntity p where  p.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();

    }

    public void removeEntity(PlaneEntity planeEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(planeEntity);
        entityManager.getTransaction().commit();
    }
}

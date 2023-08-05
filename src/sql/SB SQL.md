**Calling Stored Procedures from Spring Data JPA Repositories**


`public interface PageCatalogRepository extends JpaRepository<PageCatalogEntity , Void> {
@Query(value = "CALL firstProcedure(:page_id);", nativeQuery = true)
void deletePageCatalogById(@Param("page_id") Long page_id);
}`

`@Procedure(value = "GET_TOTAL_CARS_BY_MODEL")
int getTotalCarsByModelValue(String model);`
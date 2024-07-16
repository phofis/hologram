package mkhc.hologram.repository;

import mkhc.hologram.model.message.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<MyFile, Long> {
}

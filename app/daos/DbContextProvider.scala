package daos

import java.io.Closeable

import com.google.inject.Provider
import javax.inject.{Inject, Singleton}
import javax.sql.DataSource
import org.jdbcdslog.LogSqlDataSource
import play.api.db.Database

@Singleton
class DbContextProvider @Inject() (
  database: Database
) extends Provider[DbContext] {
  val parentDataSource: DataSource = database.dataSource match {
    case dataSource: LogSqlDataSource => dataSource.getTargetDatasource
    case _ => database.dataSource
  }

  override lazy val get: DbContext =
    new DbContext(parentDataSource.asInstanceOf[DataSource with Closeable])
}
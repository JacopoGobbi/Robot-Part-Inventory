package daos

import java.io.Closeable

import io.getquill.{H2JdbcContext, SnakeCase}
import javax.inject.Inject
import javax.sql.DataSource

class DbContext @Inject()(
  override val dataSource: DataSource with Closeable
) extends H2JdbcContext(SnakeCase, dataSource)
import io.getquill.{Escape, H2JdbcContext}

package object daos {
  type DbContext = H2JdbcContext[Escape]
}

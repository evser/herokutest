package com.epam.jmp.ratkevich.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.jmp.ratkevich.entity.FileInfo;
import com.epam.jmp.ratkevich.repository.util.ConnectionUtils;

@Repository
public class FileRepositoryImpl implements FileRepository {

	private static final String SELECT_BY_ID = "SELECT \"ID\", \"FILENAME\", \"UPLOAD_DATE\", \"EXPIRATION_DATE\", \"IS_EXPIRED\", \"DATA\" FROM FILE_INFO WHERE ID = ?";

	private static final String SELECT_ALL = "SELECT \"ID\", \"FILENAME\", \"UPLOAD_DATE\", \"EXPIRATION_DATE\", \"IS_EXPIRED\", \"DATA\" FROM FILE_INFO";

	private static final String MARK_EXPIRED = "UPDATE FILE_INFO SET IS_EXPIRED = 1 WHERE ID = ?";

	private static final String SAVE_FILE_PROCEDURE = "{call UPLOAD_FILE(?, ?, ?, ?, ?)}";

	private static final String GET_FILE_UPLOAD_DATE_PROCEDURE = "{call GET_FILE_UPLOAD_DATE(?, ?)}";

	@Autowired
	private ConnectionUtils pool;

	@Override
	public void save(FileInfo fileInfo) {
		pool.transact(connection -> {
			try (CallableStatement callableStatement = connection.prepareCall(SAVE_FILE_PROCEDURE);) {
				callableStatement.setString(1, fileInfo.getFileName());
				callableStatement.setTimestamp(2, Timestamp.valueOf(fileInfo.getUploadDate()));
				callableStatement.setTimestamp(3, Timestamp.valueOf(fileInfo.getExpirationDate()));
				callableStatement.setBoolean(4, fileInfo.isExpired());
				callableStatement.setBytes(5, fileInfo.getData());
				callableStatement.executeUpdate();
			} catch (SQLException ex) {
				System.err.println("Something went wrong with DB.");
				throw new DataAccessException(ex);
			}
		});
	}

	@Override
	public FileInfo findOne(Long id) {
		final FileInfo fileInfo = new FileInfo();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);) {
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				extractFileInfo(resultSet, fileInfo);
			}
		} catch (SQLException ex) {
			System.err.println("Something went wrong with DB.");
			throw new DataAccessException(ex);
		}
		return fileInfo;
	}

	@Override
	public LocalDateTime getUploadDate(Long id) {
		LocalDateTime uploadDate = null;
		try (Connection connection = pool.getConnection();
				CallableStatement callableStatement = connection.prepareCall(GET_FILE_UPLOAD_DATE_PROCEDURE);) {
			callableStatement.setLong(1, id);
			// callableStatement.registerOutParameter(2, JDBCType.TIMESTAMP); <<NOT IMPL. HSQLDB>> ??
			callableStatement.execute();
			uploadDate = callableStatement.getTimestamp(2).toLocalDateTime();
		} catch (SQLException ex) {
			System.err.println("Something went wrong with DB.");
			throw new DataAccessException(ex);
		}
		return uploadDate;
	}

	@Override
	public List<FileInfo> findAll() {
		final List<FileInfo> fileInfoList = new ArrayList<>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FileInfo fileInfo = new FileInfo();
				extractFileInfo(resultSet, fileInfo);
				fileInfoList.add(fileInfo);
			}
		} catch (SQLException ex) {
			System.err.println("Something went wrong with DB.");
			throw new DataAccessException(ex);
		}
		return fileInfoList;
	}

	@Override
	public void markAsExpired(Long id) {
		pool.transact(connection -> {
			try (PreparedStatement preparedStatement = connection.prepareStatement(MARK_EXPIRED);) {
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
			} catch (SQLException ex) {
				System.err.println("Something went wrong with DB.");
				throw new DataAccessException(ex);
			}
		});
	}

	private void extractFileInfo(ResultSet resultSet, FileInfo fileInfo) throws SQLException {
		fileInfo.setId(resultSet.getLong(1));
		fileInfo.setFileName(resultSet.getString(2));
		fileInfo.setUploadDate(resultSet.getTimestamp(3).toLocalDateTime());
		fileInfo.setExpirationDate(resultSet.getTimestamp(4).toLocalDateTime());
		fileInfo.setExpired(resultSet.getBoolean(5));
		fileInfo.setData(resultSet.getBytes(6));
	}

}
